package com.raghav.microservices.demo.elastic.query.service.security;

import com.raghav.microservices.demo.elastic.query.service.common.model.ElasticQueryServiceRequestModel;
import com.raghav.microservices.demo.elastic.query.service.common.model.ElasticQueryServiceResponseModel;
import com.raghav.microservices.demo.elastic.query.service.model.ElasticQueryServiceAnalyticsResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@Component
public class QueryServicePermissionEvaluator implements PermissionEvaluator {
    private static final String SUPER_USER_ROLE = "APP_SUPER_USER_ROLE";

    private final HttpServletRequest httpServletRequest;

    public QueryServicePermissionEvaluator(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object targetDomainObject,
                                 Object permission) {
        if (isSuperUser()) {
            return true;
        }
        if (targetDomainObject instanceof ElasticQueryServiceRequestModel) {
            return preAuthorize(authentication, ((ElasticQueryServiceRequestModel) targetDomainObject).getId(), permission);
        } else if (targetDomainObject instanceof ElasticQueryServiceResponseModel || targetDomainObject == null) {
            if (targetDomainObject == null) {
                return true;
            }
            ElasticQueryServiceAnalyticsResponseModel responseBody =
                    ((ResponseEntity<ElasticQueryServiceAnalyticsResponseModel>) targetDomainObject).getBody();
            return postAuthorize(authentication, responseBody.getQueryResponseModels(), permission);
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        if (isSuperUser()) {
            return true;
        }
        if (targetId == null) {
            return false;
        }
        return preAuthorize(authentication, (String) targetId, permission);
    }

    private boolean preAuthorize(Authentication authentication, String id, Object permission) {
        TwitterQueryUser twitterQueryUser = (TwitterQueryUser) authentication.getPrincipal();
        PermissionType userPermission = twitterQueryUser.getPermissions().get(id);
        return hasPermission((String) permission, userPermission);
    }

    private boolean postAuthorize(Authentication authentication,
                                  List<ElasticQueryServiceResponseModel> responseBody,
                                  Object permission) {
        TwitterQueryUser twitterQueryUser = (TwitterQueryUser) authentication.getPrincipal();
        for (ElasticQueryServiceResponseModel responseModel : responseBody) {
            PermissionType userPermission = twitterQueryUser.getPermissions().get(responseModel.getId());
            if (!hasPermission((String) permission, userPermission)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasPermission(String requiredPermission, PermissionType userPermission) {
        return userPermission != null && requiredPermission.equals(userPermission.getType());
    }

    private boolean isSuperUser() {
        return httpServletRequest.isUserInRole(SUPER_USER_ROLE);
    }
}

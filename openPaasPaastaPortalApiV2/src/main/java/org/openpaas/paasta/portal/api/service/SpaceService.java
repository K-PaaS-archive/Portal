package org.openpaas.paasta.portal.api.service;

import com.google.gson.Gson;
import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.client.v2.spaces.GetSpaceSummaryRequest;
import org.cloudfoundry.client.v2.spaces.GetSpaceSummaryResponse;
import org.cloudfoundry.client.v2.spaces.ListSpacesRequest;
import org.cloudfoundry.client.v2.spaces.SpaceResource;
import org.codehaus.jackson.map.ObjectMapper;
import org.openpaas.paasta.portal.api.model.App;
import org.openpaas.paasta.portal.api.model.Space;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * 공간 서비스 - 공간 목록 , 공간 이름 변경 , 공간 생성 및 삭제 등을 제공한다.
 *
 * @author 조민구
 * @version 1.0
 * @since 2016.12.4 최초작성
 */
@Service
public class SpaceService extends CommonService{
    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceService.class);

    public Space getSpaceSummery(CloudFoundryClient cloudFoundryClient, String spaceId) throws IOException {
        LOGGER.info("Get Space Summary: spaceId={}", spaceId);

        GetSpaceSummaryResponse spaceSummaryResponse = cloudFoundryClient.spaces().getSummary(GetSpaceSummaryRequest.builder().spaceId(spaceId).build()).block();

        Gson gson = new Gson();

        String jsonSummary = gson.toJson(spaceSummaryResponse);
        Space space = new ObjectMapper().readValue(jsonSummary, Space.class);

        int memTotal = 0;
        int memUsageTotal = 0;

        for (App app : space.getApps()) {

            memTotal += app.getMemory() * app.getInstances();

            if (app.getState().equals("STARTED")) {
                space.setAppCountStarted(space.getAppCountStarted() + 1);

                memUsageTotal += app.getMemory() * app.getInstances();

            } else if (app.getState().equals("STOPPED")) {
                space.setAppCountStopped(space.getAppCountStopped() + 1);
            } else {
                space.setAppCountCrashed(space.getAppCountCrashed() + 1);
            }
        }

        space.setMemoryLimit(memTotal);
        space.setMemoryUsage(memUsageTotal);

        return space;
    }

    public String getSpaceId(CloudFoundryClient cloudFoundryClient, String organizationId, String spaceName) {
        LOGGER.info("Get Space Id: organizationId={}, spaceName={}", organizationId, spaceName);

        List<SpaceResource> spaceList = cloudFoundryClient.spaces().list(ListSpacesRequest.builder().organizationId(organizationId).name(spaceName).build()).block().getResources();
        LOGGER.info("Get Space Id: Result size={}", spaceList.size());

        return spaceList.get(0).getMetadata().getId();
    }

}

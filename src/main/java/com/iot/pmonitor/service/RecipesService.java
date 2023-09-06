package com.iot.pmonitor.service;

import com.iot.pmonitor.enums.PartSearchEnum;
import com.iot.pmonitor.enums.StatusCdEnum;
import com.iot.pmonitor.model.ReceipesSearchModel;
import com.iot.pmonitor.request.RecipesCreateRequest;
import com.iot.pmonitor.request.RecipesUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import org.springframework.data.domain.Pageable;

public interface RecipesService {

    public PMResponse saveRecipes(RecipesCreateRequest recipesCreateRequest);

    public PMResponse updateRecipes(RecipesUpdateRequest recipesUpdateRequest);
        public PMResponse findRecipesDetails(PartSearchEnum searchEnum, String searchString, StatusCdEnum statusCdEnum, Pageable pageable, String sortParam, String pageDirection );
}

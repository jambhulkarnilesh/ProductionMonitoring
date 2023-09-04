package com.iot.pmonitor.service;

import com.iot.pmonitor.enums.SearchEnum;
import com.iot.pmonitor.model.PMSearchModel;
import com.iot.pmonitor.model.ReceipesSearchModel;
import com.iot.pmonitor.request.PartCreateRequest;
import com.iot.pmonitor.request.PartUpdateRequest;
import com.iot.pmonitor.request.RecipesCreateRequest;
import com.iot.pmonitor.request.RecipesUpdateRequest;
import com.iot.pmonitor.response.PMResponse;
import org.springframework.data.domain.Pageable;

public interface RecipesService {

    public PMResponse saveRecipes(RecipesCreateRequest recipesCreateRequest);

    public PMResponse updateRecipes(RecipesUpdateRequest recipesUpdateRequest);

    public PMResponse findReceipesDetails(ReceipesSearchModel receipesSearchModel);
}

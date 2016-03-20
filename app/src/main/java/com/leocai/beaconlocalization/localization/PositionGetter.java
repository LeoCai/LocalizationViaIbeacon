package com.leocai.beaconlocalization.localization;

import java.util.List;

/**
 * Created by leocai on 15-3-31.
 */
public interface PositionGetter {

    double [] getPosByLocalizationInfos(List<LocalizationInfo> localizationInfos);

}

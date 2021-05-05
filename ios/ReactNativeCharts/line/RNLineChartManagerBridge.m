//  Created by xudong wu on 24/02/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

#import "React/RCTViewManager.h"
#import "RNChartManagerBridge.h"
#import "RNYAxisChartManagerBridge.h"
#import "RNBarLineChartManagerBridge.h"
#import "RNLineChartManagerBridge.h"

@interface RCT_EXTERN_MODULE(RNLineChartManager, RCTViewManager)

EXPORT_BAR_LINE_CHART_BASE_PROPERTIES
EXPORT_LINE_CHART_PROPERTIES

@end

import UIKit
import Charts
import SwiftyJSON

class HighlightToDictionaryUtils: NSObject {
  static let FIELD_X: NSString  = "x"
  static let FIELD_Y: NSString = "y"
  static let FIELD_X_PX: NSString = "xPx"
  static let FIELD_Y_PX: NSString = "yPx"
  static let FIELD_DRAW_X: NSString = "drawX"
  static let FIELD_DRAW_Y: NSString = "drawY"
  static let FIELD_DATA_INDEX: NSString = "dataIndex"
  static let FIELD_DATA_SET_INDEX: NSString = "dataSetIndex"
  static let FIELD_STACK_INDEX: NSString = "stackIndex"
  static let FIELD_AXIS: NSString = "axis"
  static let FIELD_IS_STACKED: NSString = "isStacked"
  static let AXIS_LEFT: NSString = "LEFT"
  static let AXIS_RIGHT: NSString = "RIGHT"


  // TODO: use this RNChartViewBase instead of EntryToDictionaryUtils
  static func highlightToDictionary(_ h: Highlight) -> [AnyHashable: Any]{
    var dict = [AnyHashable: Any]()
    
    dict[FIELD_X] = h.x
    dict[FIELD_Y] = h.y
    dict[FIELD_X_PX] = h.xPx
    dict[FIELD_Y_PX] = h.yPx
    dict[FIELD_DRAW_X] = h.drawX
    dict[FIELD_DRAW_Y] = h.drawY
    dict[FIELD_DATA_INDEX] = h.dataIndex
    dict[FIELD_DATA_SET_INDEX] = h.dataSetIndex
    dict[FIELD_STACK_INDEX] = h.stackIndex
    dict[FIELD_IS_STACKED] = h.isStacked
    dict[FIELD_AXIS] = h.axis == YAxis.AxisDependency.left ? AXIS_LEFT : AXIS_RIGHT
    
    return dict
  }
}

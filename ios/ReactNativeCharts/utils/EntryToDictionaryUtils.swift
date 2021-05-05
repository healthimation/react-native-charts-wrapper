//
//  EntryToDictionaryUtils.swift
//  
//
//  Created by xudong wu on 08/03/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

import UIKit
import Charts
import SwiftyJSON

class EntryToDictionaryUtils: NSObject {
  static func entryToDictionary(_ entry: ChartDataEntry) -> [AnyHashable: Any]{
    var dict = [AnyHashable: Any]()
    
    if entry.data != nil {
        dict["data"] = (entry.data as! JSON).dictionaryObject!
    }
    
    if entry is BarChartDataEntry {        
        let barEntry = entry as! BarChartDataEntry;

        dict["x"] = barEntry.x
        
        if barEntry.yValues != nil {
            dict["yValues"] = barEntry.yValues
        } else {
            dict["y"] = barEntry.y
        }
    } else if entry is BubbleChartDataEntry {
        
        let bubbleEntry = entry as! BubbleChartDataEntry;
        
        dict["x"] = bubbleEntry.x
        dict["y"] = bubbleEntry.y
        
        dict["size"] = bubbleEntry.size
    } else if entry is CandleChartDataEntry {
        let candleEntry = entry as! CandleChartDataEntry;
        
        dict["x"] = candleEntry.x
        dict["open"] = candleEntry.open
        dict["close"] = candleEntry.close
        dict["low"] = candleEntry.low
        dict["high"] = candleEntry.high
    } else if entry is PieChartDataEntry {
        let pieEntry = entry as! PieChartDataEntry;
        
        dict["value"] = pieEntry.value
        
        if pieEntry.label != nil {
            dict["label"] = pieEntry.label!
        }            
    } else if entry is RadarChartDataEntry {
        let radarEntry = entry as! RadarChartDataEntry;
        
        dict["value"] = radarEntry.value
    } else {
        dict["x"] = entry.x
        dict["y"] = entry.y
    }
    
    return dict
  }

  static let FIELD_X: NSString  = "highlight_x"
  static let FIELD_Y: NSString = "highlight_y"
  static let FIELD_X_PX: NSString = "highlight_x_px"
  static let FIELD_Y_PX: NSString = "highlight_y_px"
  static let FIELD_DRAW_X: NSString = "highlight_draw_x"
  static let FIELD_DRAW_Y: NSString = "highlight_draw_y"
  static let FIELD_DATA_INDEX: NSString = "highlight_data_index"
  static let FIELD_DATA_SET_INDEX: NSString = "highlight_data_set_index"
  static let FIELD_STACK_INDEX: NSString = "highlight_stack_index"
  static let FIELD_AXIS: NSString = "highlight_axis"
  static let FIELD_IS_STACKED: NSString = "highlight_is_stacked"
  static let AXIS_LEFT: NSString = "LEFT"
  static let AXIS_RIGHT: NSString = "RIGHT"


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

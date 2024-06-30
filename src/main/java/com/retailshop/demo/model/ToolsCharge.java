package com.retailshop.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToolsCharge {
    private String toolType;
    private Double dailyCharge;
    private boolean isWeekdayCharge;
    private boolean isWeekendCharge;
    private boolean isHolidayCharge;
}

    public static OperableTrigger newTrigger(Map<String, Object> attrMap) throws ParseException {
        SimpleTriggerImpl result = new SimpleTriggerImpl();
        if(attrMap.containsKey("repeatCount")) {
            result.setRepeatCount(((Integer) attrMap.get("repeatCount")).intValue());
        }
        if(attrMap.containsKey("repeatInterval")) {
            result.setRepeatInterval(((Long) attrMap.get("repeatInterval")).longValue());
        }
        if(attrMap.containsKey("timesTriggered")) {
            result.setTimesTriggered(((Integer) attrMap.get("timesTriggered")).intValue());
        }
        TriggerSupport.initializeTrigger(result, attrMap);
        return result;
    }

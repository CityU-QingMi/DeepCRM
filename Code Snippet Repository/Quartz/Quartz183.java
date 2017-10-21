    public static List<CompositeData> toCompositeList(List<? extends Trigger> triggers) {
        List<CompositeData> result = new ArrayList<CompositeData>();
        for(Trigger trigger : triggers) {
            CompositeData cData = TriggerSupport.toCompositeData(trigger);
            if(cData != null) {
                result.add(cData);
            }
        }
        return result;
    }

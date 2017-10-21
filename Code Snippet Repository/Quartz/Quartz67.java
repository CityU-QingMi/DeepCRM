    @Override
    public MutableTrigger build() {

        SimpleTriggerImpl st = new SimpleTriggerImpl();
        st.setRepeatInterval(interval);
        st.setRepeatCount(repeatCount);
        st.setMisfireInstruction(misfireInstruction);
        
        return st;
    }

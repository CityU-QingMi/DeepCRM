    protected void populateParams() {
        super.populateParams();

        IteratorComponent tag = (IteratorComponent) getComponent();
        tag.setStatus(statusAttr);
        tag.setValue(value);
        tag.setBegin(begin);
        tag.setEnd(end);
        tag.setStep(step);
    }

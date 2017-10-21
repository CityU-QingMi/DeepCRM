    public void evaluateExtraParams() {
        super.evaluateExtraParams();
        StringBuilder onchangeParam = new StringBuilder();
        onchangeParam.append(getParameters().get("id")).append("Redirect(this.selectedIndex)");
        if(StringUtils.isNotEmpty(this.onchange)) {
        	onchangeParam.append(";").append(this.onchange);
        }
        // force the onchange parameter
        addParameter("onchange", onchangeParam.toString());
    }

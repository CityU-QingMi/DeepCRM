    protected void applyScriptingAttrs(UIBean bean) {
        bean.setOnclick(scriptingAttrs.get("onclick"));
        bean.setOndblclick(scriptingAttrs.get("ondblclick"));
        bean.setOnmousedown(scriptingAttrs.get("onmousedown"));
        bean.setOnmouseup(scriptingAttrs.get("onmouseup"));
        bean.setOnmouseover(scriptingAttrs.get("onmouseover"));
        bean.setOnmousemove(scriptingAttrs.get("onmousemove"));
        bean.setOnmouseout(scriptingAttrs.get("onmouseout"));
        bean.setOnfocus(scriptingAttrs.get("onfocus"));
        bean.setOnblur(scriptingAttrs.get("onblur"));
        bean.setOnkeypress(scriptingAttrs.get("onkeypress"));
        bean.setOnkeydown(scriptingAttrs.get("onkeydown"));
        bean.setOnkeyup(scriptingAttrs.get("onkeyup"));
        bean.setOnselect(scriptingAttrs.get("onselect"));
        bean.setOnchange(scriptingAttrs.get("onchange"));
    }

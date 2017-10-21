    protected void populateParams() {
        super.populateParams();

        UIBean uiBean = (UIBean) component;
        uiBean.setCssClass(cssClass);
        uiBean.setCssStyle(cssStyle);
        uiBean.setCssErrorClass(cssErrorClass);
        uiBean.setCssErrorStyle(cssErrorStyle);
        uiBean.setTitle(title);
        uiBean.setDisabled(disabled);
        uiBean.setLabel(label);
        uiBean.setLabelSeparator(labelSeparator);
        uiBean.setLabelposition(labelposition);
        uiBean.setRequiredPosition(requiredPosition);
        uiBean.setErrorPosition(errorPosition);
        uiBean.setName(name);
        uiBean.setRequiredLabel(requiredLabel);
        uiBean.setTabindex(tabindex);
        uiBean.setValue(value);
        uiBean.setTemplate(template);
        uiBean.setTheme(theme);
        uiBean.setTemplateDir(templateDir);
        uiBean.setOnclick(onclick);
        uiBean.setOndblclick(ondblclick);
        uiBean.setOnmousedown(onmousedown);
        uiBean.setOnmouseup(onmouseup);
        uiBean.setOnmouseover(onmouseover);
        uiBean.setOnmousemove(onmousemove);
        uiBean.setOnmouseout(onmouseout);
        uiBean.setOnfocus(onfocus);
        uiBean.setOnblur(onblur);
        uiBean.setOnkeypress(onkeypress);
        uiBean.setOnkeydown(onkeydown);
        uiBean.setOnkeyup(onkeyup);
        uiBean.setOnselect(onselect);
        uiBean.setOnchange(onchange);
        uiBean.setTooltip(tooltip);
        uiBean.setTooltipConfig(tooltipConfig);
        uiBean.setJavascriptTooltip(javascriptTooltip);
        uiBean.setTooltipCssClass(tooltipCssClass);
        uiBean.setTooltipDelay(tooltipDelay);
        uiBean.setTooltipIconPath(tooltipIconPath);
        uiBean.setAccesskey(accesskey);
        uiBean.setKey(key);
        uiBean.setId(id);

        uiBean.setDynamicAttributes(dynamicAttributes);
    }

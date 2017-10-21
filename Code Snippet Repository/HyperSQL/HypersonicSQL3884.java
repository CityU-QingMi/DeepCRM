    void resetTooltips() {

        Iterator   it = tipMap.keySet().iterator();
        JComponent component;

        while (it.hasNext()) {
            component = (JComponent) it.next();

            component.setToolTipText(showTooltips
                                     ? ((String) tipMap.get(component))
                                     : (String) null);
        }
    }

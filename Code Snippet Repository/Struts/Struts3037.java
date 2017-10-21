    public static Object getValueFromPropertyEditorManager(
            Class attrClass, String attrName, String attrValue)
            throws JasperException {
        try {
            PropertyEditor propEditor =
                    PropertyEditorManager.findEditor(attrClass);
            if (propEditor != null) {
                propEditor.setAsText(attrValue);
                return propEditor.getValue();
            } else {
                throw new IllegalArgumentException(
                        Localizer.getMessage("jsp.error.beans.propertyeditor.notregistered"));
            }
        } catch (IllegalArgumentException ex) {
            throw new JasperException(
                    Localizer.getMessage("jsp.error.beans.property.conversion",
                            attrValue, attrClass.getName(), attrName,
                            ex.getMessage()));
        }
    }

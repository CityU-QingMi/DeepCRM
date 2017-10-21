    public void buildParametersString(Map<String, Object> params, StringBuilder link, String paramSeparator, boolean encode) {
        if ((params != null) && (params.size() > 0)) {
            if (!link.toString().contains("?")) {
                link.append("?");
            } else {
                link.append(paramSeparator);
            }

            // Set params
            Iterator<Map.Entry<String, Object>> iter = params.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, Object> entry = iter.next();
                String name = entry.getKey();
                Object value = entry.getValue();

                if (value instanceof Iterable) {
                    for (Iterator iterator = ((Iterable) value).iterator(); iterator.hasNext();) {
                        Object paramValue = iterator.next();
                        link.append(buildParameterSubstring(name, paramValue != null ? paramValue.toString() : StringUtils.EMPTY, encode));

                        if (iterator.hasNext()) {
                            link.append(paramSeparator);
                        }
                    }
                } else if (value instanceof Object[]) {
                    Object[] array = (Object[]) value;
                    for (int i = 0; i < array.length; i++) {
                        Object paramValue = array[i];
                        link.append(buildParameterSubstring(name, paramValue != null ? paramValue.toString() : StringUtils.EMPTY, encode));

                        if (i < array.length - 1) {
                            link.append(paramSeparator);
                        }
                    }
                } else {
                    link.append(buildParameterSubstring(name, value != null ? value.toString() : StringUtils.EMPTY, encode));
                }

                if (iter.hasNext()) {
                    link.append(paramSeparator);
                }
            }
        }
    }

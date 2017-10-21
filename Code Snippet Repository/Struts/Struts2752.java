        private String createTagVarName(String fullName, String prefix,
                String shortName) {

            String varName;
            synchronized (tagVarNumbers) {
                varName = prefix + "_" + shortName + "_";
                if (tagVarNumbers.get(fullName) != null) {
                    Integer i = (Integer) tagVarNumbers.get(fullName);
                    varName = varName + i.intValue();
                    tagVarNumbers.put(fullName, new Integer(i.intValue() + 1));
                } else {
                    tagVarNumbers.put(fullName, new Integer(1));
                    varName = varName + "0";
                }
            }
            return JspUtil.makeJavaIdentifier(varName);
        }

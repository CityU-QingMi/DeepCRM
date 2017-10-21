        public TagInfo getTagInfo() throws JasperException {

            if (name == null) {
                // XXX Get it from tag file name
            }

            if (bodycontent == null) {
                bodycontent = TagInfo.BODY_CONTENT_SCRIPTLESS;
            }

            String tagClassName = JspUtil.getTagHandlerClassName(
                    path, tagLibInfo.getReliableURN(), err);

            TagVariableInfo[] tagVariableInfos = new TagVariableInfo[variableVector
                    .size()];
            variableVector.copyInto(tagVariableInfos);

            TagAttributeInfo[] tagAttributeInfo = new TagAttributeInfo[attributeVector
                    .size()];
            attributeVector.copyInto(tagAttributeInfo);

            return new JasperTagInfo(name, tagClassName, bodycontent,
                    description, tagLibInfo, tei, tagAttributeInfo,
                    displayName, smallIcon, largeIcon, tagVariableInfos,
                    dynamicAttrsMapName);
        }

    public JasperTagInfo(String tagName,
			 String tagClassName,
			 String bodyContent,
			 String infoString,
			 TagLibraryInfo taglib,
			 TagExtraInfo tagExtraInfo,
			 TagAttributeInfo[] attributeInfo,
			 String displayName,
			 String smallIcon,
			 String largeIcon,
			 TagVariableInfo[] tvi,
			 String mapName) {

	super(tagName, tagClassName, bodyContent, infoString, taglib,
	      tagExtraInfo, attributeInfo, displayName, smallIcon, largeIcon,
	      tvi);
	this.dynamicAttrsMapName = mapName;
    }

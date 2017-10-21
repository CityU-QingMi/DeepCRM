    protected Map createPropertyMap(InternalContextAdapter contextAdapter, Node node) throws ParseErrorException, MethodInvocationException {
        Map propertyMap;

        int children = node.jjtGetNumChildren();
        if (getType() == BLOCK) {
            children--;
        }

        // Velocity supports an on-the-fly Map-definition syntax that leads
        // to more readable and faster code:
        //
        //    #url({'id':'url', 'action':'MyAction'})
        //
        // We support this syntax by checking for a single Map argument
        // to any directive and using that as the property map instead
        // of building one from individual name-value pair strings.
        Node firstChild = null;
        Object firstValue = null;
        if(children == 1
           && null != (firstChild = node.jjtGetChild(0))
           && null != (firstValue = firstChild.value(contextAdapter))
           && firstValue instanceof Map) {
            propertyMap = (Map)firstValue;
        } else {
            propertyMap = new HashMap();

            for (int index = 0, length = children; index < length; index++) {
                this.putProperty(propertyMap, contextAdapter, node.jjtGetChild(index));
            }
        }

        return propertyMap;
    }

    public void testGetContent1() throws Exception {
        // set up Node
        Node mockNode = (Node) createMock(Node.class);

        expect(mockNode.getNodeValue()).andStubReturn("testing testing 123");
        expect(mockNode.getNodeType()).andStubReturn(Node.TEXT_NODE);


        // set up NodeList
        NodeList mockNodeList = (NodeList) createMock(NodeList.class);

        expect(mockNodeList.getLength()).andStubReturn(1);
        expect(mockNodeList.item(0)).andStubReturn(mockNode);


        // set up Element
        Element mockElement = (Element) createMock(Element.class);

        expect(mockElement.getChildNodes()).andStubReturn(mockNodeList);

        replay(mockNode, mockNodeList, mockElement);

        String result = XmlHelper.getContent(mockElement);

        assertEquals(result, "testing testing 123");
        
        verify(mockNode, mockNodeList, mockElement);
    }

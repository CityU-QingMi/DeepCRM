    public void testShouldMergePluginConfigurationSubItemsWithMergeAttributeSet()
        throws XmlPullParserException, IOException
    {
        String parentConfigStr = "<configuration><items><item>one</item><item>two</item></items></configuration>";
        Xpp3Dom parentConfig = Xpp3DomBuilder.build( new StringReader( parentConfigStr ) );

        Plugin parentPlugin = createPlugin( "group", "artifact", "1", null );
        parentPlugin.setConfiguration( parentConfig );

        String childConfigStr = "<configuration><items combine.children=\"append\"><item>three</item></items></configuration>";
        Xpp3Dom childConfig = Xpp3DomBuilder.build( new StringReader( childConfigStr ) );

        Plugin childPlugin = createPlugin( "group", "artifact", "1", null );
        childPlugin.setConfiguration( childConfig );

        ModelUtils.mergePluginDefinitions( childPlugin, parentPlugin, true );

        Xpp3Dom result = (Xpp3Dom) childPlugin.getConfiguration();
        Xpp3Dom items = result.getChild( "items" );

        assertEquals( 3, items.getChildCount() );

        Xpp3Dom[] item = items.getChildren();

        List<String> actual = Arrays.asList( item[0].getValue(), item[1].getValue(), item[2].getValue() );
        List<String> expected = Arrays.asList( "one", "two", "three" );
        Collections.sort( actual );
        Collections.sort( expected );
        assertEquals( expected, actual );
    }

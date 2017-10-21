	public static Element addNativelyGeneratedId(
			Element parent, String name, String type,
			boolean useRevisionEntityWithNativeId) {
		final Element idMapping = parent.addElement( "id" );
		idMapping.addAttribute( "name", name ).addAttribute( "type", type );

		final Element generatorMapping = idMapping.addElement( "generator" );
		if ( useRevisionEntityWithNativeId ) {
			generatorMapping.addAttribute( "class", "native" );
		}
		else {
			generatorMapping.addAttribute( "class", "org.hibernate.envers.enhanced.OrderedSequenceGenerator" );
			generatorMapping.addElement( "param" ).addAttribute( "name", "sequence_name" ).setText(
					"REVISION_GENERATOR"
			);
			generatorMapping.addElement( "param" )
					.addAttribute( "name", "table_name" )
					.setText( "REVISION_GENERATOR" );
			generatorMapping.addElement( "param" ).addAttribute( "name", "initial_value" ).setText( "1" );
			generatorMapping.addElement( "param" ).addAttribute( "name", "increment_size" ).setText( "1" );
		}
//        generatorMapping.addAttribute("class", "sequence");
//        generatorMapping.addElement("param").addAttribute("name", "sequence").setText("custom");

		return idMapping;
	}

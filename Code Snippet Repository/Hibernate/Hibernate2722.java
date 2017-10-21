	private void typeDiscriminator(AST path) throws SemanticException {
		if ( path == null ) {
			throw new SemanticException( "type() discriminator reference has no path!" );
		}

		FromReferenceNode pathAsFromReferenceNode = (FromReferenceNode) path;
		FromElement fromElement = pathAsFromReferenceNode.getFromElement();
		TypeDiscriminatorMetadata typeDiscriminatorMetadata = fromElement.getTypeDiscriminatorMetadata();

		setDataType( typeDiscriminatorMetadata.getResolutionType() );
		setText( typeDiscriminatorMetadata.getSqlFragment() );
		setType( SqlTokenTypes.SQL_TOKEN );
	}

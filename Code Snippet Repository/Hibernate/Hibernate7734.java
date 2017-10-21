	public MultipleCollectionEntity deepCopy() {
		MultipleCollectionEntity clone = new MultipleCollectionEntity();
		clone.setText(this.text);
		clone.setId(this.id);
		
		for (MultipleCollectionRefEntity1 refEntity1 : refEntities1) {
			clone.addRefEntity1(refEntity1.deepCopy(clone));
		}
		for (MultipleCollectionRefEntity2 refEntity2 : refEntities2) {
			clone.addRefEntity2(refEntity2.deepCopy(clone));
		}
		return clone;
	}

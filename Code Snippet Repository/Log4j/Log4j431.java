    @Test
    public void testDeserializeNonSerializableParamEqualIfToStringSame() {
        class NonSerializable {
            @Override
            public boolean equals(final Object other) {
                return other instanceof NonSerializable; // a very lenient equals()
            }
        }
        final NonSerializable nonSerializable = new NonSerializable();
        assertFalse(nonSerializable instanceof Serializable);
        final ObjectMessage msg = new ObjectMessage(nonSerializable);
        final ObjectMessage other = SerialUtil.deserialize(SerialUtil.serialize(msg));

        assertEquals(msg, other);
        assertEquals(other, msg);
    }

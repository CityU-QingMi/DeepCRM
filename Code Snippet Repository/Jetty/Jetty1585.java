    @Test
    public void testGetMatches()
    {
        List<MappedResource<String>> matches = mappings.getMatches(inputPath);

        StringBuilder actual = new StringBuilder();
        actual.append('[');
        boolean delim = false;
        for (MappedResource<String> res : matches)
        {
            if (delim)
                actual.append(", ");
            actual.append(res.getPathSpec().pathSpec).append('=').append(res.getResource());
            delim = true;
        }
        actual.append(']');

        assertThat(message + " on [" + inputPath + "]",actual.toString(),is(expectedListing));
    }

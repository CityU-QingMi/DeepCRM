    public void doTestAddingAndModifyingCollectionWithObjects(Collection barColl) {

        ValueStack vs = ActionContext.getContext().getValueStack();
        Foo foo = new Foo();

        foo.setBarCollection(barColl);
        Bar bar1 = new Bar();
        bar1.setId(new Long(11));
        barColl.add(bar1);
        Bar bar2 = new Bar();
        bar2.setId(new Long(22));
        barColl.add(bar2);
        //try modifying bar1 and bar2
        //check the logs here to make sure
        //the Map is being created
        ReflectionContextState.setCreatingNullObjects(vs.getContext(), true);
        ReflectionContextState.setReportingConversionErrors(vs.getContext(), true);
        vs.push(foo);
        String bar1Title = "The Phantom Menace";
        String bar2Title = "The Clone Wars";
        vs.setValue("barCollection(22).title", bar2Title);
        vs.setValue("barCollection(11).title", bar1Title);
        for (Object aBarColl : barColl) {
            Bar next = (Bar) aBarColl;
            if (next.getId().intValue() == 22) {
                assertEquals(bar2Title, next.getTitle());
            } else {
                assertEquals(bar1Title, next.getTitle());
            }
        }
        //now test adding to a collection
        String bar3Title = "Revenge of the Sith";
        String bar4Title = "A New Hope";
        vs.setValue("barCollection.makeNew[4].title", bar4Title, true);
        vs.setValue("barCollection.makeNew[0].title", bar3Title, true);

        assertEquals(4, barColl.size());

        for (Object aBarColl : barColl) {
            Bar next = (Bar) aBarColl;
            if (next.getId() == null) {
                assertNotNull(next.getTitle());
                assertTrue(next.getTitle().equals(bar4Title)
                        || next.getTitle().equals(bar3Title));
            }
        }

    }

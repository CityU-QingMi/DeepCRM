    public void compareLists(HsqlArrayList arrayList, HsqlDeque linkedList,
                             Vector vector) {

        boolean arrayListError  = false;
        boolean linkedListError = false;

        if (!equalsVector(arrayList, vector)) {
            System.out.println("Error in array list implementation");

            arrayListError = true;
        }

        if (!equalsVector(linkedList, vector)) {
            System.out.println("Error in linked list implementation");

            linkedListError = true;
        }

        if (arrayListError || linkedListError) {
            this.printListCommandsCalled(listCommandsCalled);
            System.out.flush();
            fail("test failed");
        }
    }

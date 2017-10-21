    @SuppressWarnings("")
    @Test
    public void resolveAsyncAttributes() {

        TestBean testBean1 = new TestBean("Bean1");
        TestBean testBean2 = new TestBean("Bean2");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("attr1", Mono.just(testBean1));
        attributes.put("attr2", Flux.just(testBean1, testBean2));
        attributes.put("attr3", Single.just(testBean2));
        attributes.put("attr4", Observable.just(testBean1, testBean2));
        attributes.put("attr5", Mono.empty());

        TestView view = new TestView();
        StepVerifier.create(view.render(attributes, null, this.exchange)).verifyComplete();

        assertEquals(testBean1, view.attributes.get("attr1"));
        assertArrayEquals(new TestBean[] {testBean1, testBean2}, ((List<TestBean>)view.attributes.get("attr2")).toArray());
        assertEquals(testBean2, view.attributes.get("attr3"));
        assertArrayEquals(new TestBean[] {testBean1, testBean2}, ((List<TestBean>)view.attributes.get("attr4")).toArray());
        assertNull(view.attributes.get("attr5"));
    }

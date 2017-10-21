        @Override
        public void scanJars(WebAppContext context, Collection<Resource> jars, boolean useCaches, List<String> scanTypes) throws Exception
        {
            assertNotNull(scanTypes);
            List<String> expectedScanTypes = null;
            switch (_invocationCount)
            {
                case 0: 
                {
                    expectedScanTypes = _expectedContainerScanTypes;
                    break;
                }
                case 1:
                {
                    expectedScanTypes = _expectedWebAppScanTypes;
                    break;
                }
                default:
                {
                    fail("Too many invocations");
                }
            }

            ++_invocationCount;

            assertNotNull(expectedScanTypes);
            assertTrue(expectedScanTypes.containsAll(scanTypes));
            assertEquals(expectedScanTypes.size(), scanTypes.size());
        }

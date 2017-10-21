    private void invokeFragmentActivators(ServiceReference sr)
    {
        PackageAdmin admin = (PackageAdmin) _context.getService(sr);
        Bundle[] fragments = admin.getFragments(_context.getBundle());
        if (fragments == null) { return; }
        for (Bundle frag : fragments)
        {
            // find a convention to look for a class inside the fragment.
            try
            {
                String fragmentActivator = frag.getSymbolicName() + ".FragmentActivator";
                Class<?> c = Class.forName(fragmentActivator);
                if (c != null)
                {
                    BundleActivator bActivator = (BundleActivator) c.newInstance();
                    bActivator.start(_context);
                    _activatedFragments.add(bActivator);
                }
            }
            catch (NullPointerException e)
            {
                // e.printStackTrace();
            }
            catch (InstantiationException e)
            {
                // e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                // e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                // e.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

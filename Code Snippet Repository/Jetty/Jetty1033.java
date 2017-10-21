    private void complete()
    {
        lease.recycle();

        actives.forEach(Entry::complete);

        if (stalled != null)
        {
            // We have written part of the frame, but there is more to write.
            // The API will not allow to send two data frames for the same
            // stream so we append the unfinished frame at the end to allow
            // better interleaving with other streams.
            int index = actives.indexOf(stalled);
            for (int i = index; i < actives.size(); ++i)
            {
                Entry entry = actives.get(i);
                if (entry.dataRemaining() > 0)
                    append(entry);
            }
            for (int i = 0; i < index; ++i)
            {
                Entry entry = actives.get(i);
                if (entry.dataRemaining() > 0)
                    append(entry);
            }
            stalled = null;
        }

        actives.clear();
    }

package de.bunk.transformer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTransformer<In, Out> {

    @Nullable
    public abstract Out transform(@Nullable In in);

    @NotNull
    public List<Out> transform(@Nullable List<In> inList) {
        final List<Out> list = new ArrayList<>();

        if (inList == null) {
            return list;
        }

        for (In in : inList) {
            Out out = transform(in);

            if (out != null) {
                list.add(out);
            }
        }

        return list;
    }
}

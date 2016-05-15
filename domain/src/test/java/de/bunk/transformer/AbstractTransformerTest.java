package de.bunk.transformer;

import com.fernandocejas.arrow.collections.Lists;

import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AbstractTransformerTest {
    private AbstractTransformer<Object, Object> abstractTransformer;

    @Before
    public void setUp() {
        abstractTransformer = new AbstractTransformer<Object, Object>() {
            @Nullable
            @Override
            public Object transform(@Nullable Object o) {
                return o;
            }
        };
    }

    @Test
    public void transform_Should_ReturnEmptyList_When_ListIsEmpty() {
        Assert.assertTrue(abstractTransformer.transform(Lists.newArrayList()).isEmpty());
    }

    @Test
    public void transform_Should_ReturnEmptyList_When_ListIsNull() {
        Assert.assertTrue(abstractTransformer.transform(null).isEmpty());
    }

    @Test
    public void transform_Should_FilterOutNullElements() {
        final List<Object> objectList = Lists.newArrayList(new Object(), null, new Object());

        final List<Object> transformedList = abstractTransformer.transform(objectList);

        Assert.assertEquals(objectList.size() - 1, transformedList.size());
    }

    @Test
    public void transform_Should_ReturnSameSize() {
        final List<Object> objectList = Lists.newArrayList(new Object(), new Object());

        final List<Object> transformedList = abstractTransformer.transform(objectList);

        Assert.assertEquals(objectList.size(), transformedList.size());
    }
}
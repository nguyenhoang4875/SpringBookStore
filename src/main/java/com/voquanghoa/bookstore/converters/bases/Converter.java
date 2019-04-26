package com.voquanghoa.bookstore.converters.bases;

import com.voquanghoa.bookstore.exceptions.BadRequestException;

import java.util.ArrayList;
import java.util.List;

public abstract class Converter<S, T> {

    public abstract T convert(S source) throws BadRequestException;

    public List<T> convert(List<S> sources) throws BadRequestException {
        ArrayList<T> result = new ArrayList<>();

        for (S source: sources) {
            result.add(convert(source));
        }

        return result;
    }
}

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016. Diorite (by Bartłomiej Mazur (aka GotoFinal))
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.diorite.inject.scopes;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

import org.diorite.inject.ScopeHandler;
import org.diorite.inject.Singleton;
import org.diorite.inject.binder.DynamicProvider;

/**
 * Implementation of {@link Singleton} scope.
 *
 * @param <T>
 *         type of object.
 */
public class SingletonScopeHandler<T> implements ScopeHandler<T, Singleton>
{
    private AtomicBoolean invoked = new AtomicBoolean(false);
    @Nullable
    private T value;

    @Override
    public DynamicProvider<T> apply(DynamicProvider<T> dynamicProvider, Singleton scope)
    {
        if (this.invoked.get())
        {
            return (object, data) -> this.value;
        }
        return (object, data) ->
        {
            if (this.invoked.getAndSet(true))
            {
                return this.value;
            }
            synchronized (this)
            {
                this.value = dynamicProvider.tryToGet(object, data);
                return this.value;
            }
        };
    }
}

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

package org.diorite.config.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.diorite.config.serialization.DeserializationData;
import org.diorite.config.serialization.SerializationData;

/**
 * Used to mark class and that should be used for serialization. <br>
 * Serialization method must be matching one of this patterns:
 * <ol>
 * <li>static void nameOfMethod(T, {@link SerializationData})</li>
 * <li>static void nameOfMethod({@link SerializationData}, T)</li>
 * <li>static void nameOfMethod(Object, {@link SerializationData})</li>
 * <li>static void nameOfMethod({@link SerializationData}, Object)</li>
 * <li>void nameOfMethod({@link SerializationData})</li>
 * </ol>
 * Deserialization method must be matching one of this patterns:
 * <ol>
 * <li>static T nameOfMethod({@link DeserializationData})</li>
 * <li>constructor({@link DeserializationData})</li>
 * </ol>
 *
 * @see org.diorite.config.serialization.Serializable
 * @see org.diorite.config.serialization.Serializer
 */
@Documented
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Serializable
{
}

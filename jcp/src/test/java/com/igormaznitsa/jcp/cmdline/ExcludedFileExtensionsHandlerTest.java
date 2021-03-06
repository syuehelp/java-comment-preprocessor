/*
 * Copyright 2002-2019 Igor Maznitsa (http://www.igormaznitsa.com)
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.igormaznitsa.jcp.cmdline;

import com.igormaznitsa.jcp.context.PreprocessorContext;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ExcludedFileExtensionsHandlerTest extends AbstractCommandLineHandlerTest {

  private static final ExcludedFileExtensionsHandler HANDLER = new ExcludedFileExtensionsHandler();

  @Override
  public void testThatTheHandlerInTheHandlerList() {
    assertHandlerInTheHandlerList(HANDLER);
  }

  @Override
  public void testExecution() throws Exception {
    final PreprocessorContext mock = prepareMockContext();

    assertFalse(HANDLER.processCommandLineKey("", mock));
    assertFalse(HANDLER.processCommandLineKey("/ef:", mock));
    assertFalse(HANDLER.processCommandLineKey("/ef", mock));
    assertFalse(HANDLER.processCommandLineKey("/EF:", mock));

    verify(mock, never()).setExcludeExtensions(any());

    assertTrue(HANDLER.processCommandLineKey("/ef:rrr,Ggg,bBb", mock));
    verify(mock).setExcludeExtensions(Arrays.asList("rrr", "Ggg", "bBb"));
  }

  @Override
  public void testName() {
    assertEquals("/EF:", HANDLER.getKeyName());
  }

  @Override
  public void testDescription() {
    assertDescription(HANDLER);
  }

}

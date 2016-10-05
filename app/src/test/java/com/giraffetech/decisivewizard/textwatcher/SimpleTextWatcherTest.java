package com.giraffetech.decisivewizard.textwatcher;

import android.text.Editable;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class SimpleTextWatcherTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private SimpleTextWatcher.SimpleTextWatcherListener mSimpleTextWatcherListener;

    //Class under test
    private SimpleTextWatcher mSimpleTextWatcher = new SimpleTextWatcher();

    @Test
    public void afterTextChanged_ZeroStringLength_DoesNothing() {
        mSimpleTextWatcher.afterTextChanged(editableWithString(""));
    }

    @Test
    public void afterTextChanged_LastCharNotNewLine_DoesNothing() {
        mSimpleTextWatcher.afterTextChanged(editableWithString("Giraffe"));
    }

    @Test
    public void afterTextChanged_LastCharNewLine_NullListener_RemovesTheNewLine() {
        Editable editable = editableWithString("Giraffe\n");

        mSimpleTextWatcher.afterTextChanged(editable);

        assertEquals("Giraffe", editable.toString());
    }

    @Test
    public void afterTextChanged_LastCharNewLine_WithListener_CallsTheListener() {
        Editable editable = editableWithString("Giraffe\n");
        SimpleTextWatcher.SimpleTextWatcherListener mockListener = mSimpleTextWatcherListener;
        mSimpleTextWatcher.setListener(mockListener);

        mSimpleTextWatcher.afterTextChanged(editable);

        verify(mockListener).onEnterKeyPressed();
    }

    @Test
    public void afterTextChanged_LastCharNewLine_WithListener_RemovesTheNewLine() {
        Editable editable = editableWithString("Giraffe\n");
        mSimpleTextWatcher.setListener(mSimpleTextWatcherListener);

        mSimpleTextWatcher.afterTextChanged(editable);

        assertEquals("Giraffe", editable.toString());
    }

    //region Helper Methods
    private Editable editableWithString(String string) {
        return Editable.Factory.getInstance().newEditable(string);
    }
    //endregion Helper Methods

}

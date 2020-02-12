package com.otaliastudios.cameraview;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.filters.SmallTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class CropHelperTest extends BaseTest {

    @Test
    public void testCropFromYuv() {
        testCropFromYuv(1600, 1600, AspectRatio.of(16, 9));
        testCropFromYuv(1600, 1600, AspectRatio.of(9, 16));
    }

    @Test
    public void testCropFromJpeg() {
        testCropFromJpeg(1600, 1600, AspectRatio.of(16, 9));
        testCropFromJpeg(1600, 1600, AspectRatio.of(9, 16));
    }

    private void testCropFromYuv(final int w, final int h, final AspectRatio target) {
        final boolean wider = target.toFloat() > ((float) w / (float) h);
        byte[] b = CropHelper.cropToJpeg(mockYuv(w, h), target, 100);
        Bitmap result = BitmapFactory.decodeByteArray(b, 0, b.length);

        // Assert.
        AspectRatio ratio = AspectRatio.of(result.getWidth(), result.getHeight());
        assertEquals(target, ratio);
        if (wider) { // width must match.
            assertEquals(result.getWidth(), w);
        } else {
            assertEquals(result.getHeight(), h);
        }
    }

    private void testCropFromJpeg(int w, int h, AspectRatio target) {
        final boolean wider = target.toFloat() > ((float) w / (float) h);
        byte[] b = CropHelper.cropToJpeg(mockJpeg(w, h), target, 100);
        Bitmap result = BitmapFactory.decodeByteArray(b, 0, b.length);

        // Assert.
        AspectRatio ratio = AspectRatio.of(result.getWidth(), result.getHeight());
        assertEquals(target, ratio);
        if (wider) { // width must match.
            assertEquals(result.getWidth(), w);
        } else {
            assertEquals(result.getHeight(), h);
        }
    }
}

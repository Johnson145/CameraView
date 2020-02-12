package com.otaliastudios.cameraview;


import android.content.Context;
import androidx.test.filters.SmallTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.view.ViewGroup;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class TextureCameraPreviewTest extends PreviewTest {

    @Override
    protected CameraPreview createPreview(Context context, ViewGroup parent, CameraPreview.SurfaceCallback callback) {
        return new TextureCameraPreview(context, parent, callback);
    }

    @Override
    protected void ensureAvailable() {
        if (isHardwareAccelerated()) {
            super.ensureAvailable();
        } else {
            preview.onSurfaceAvailable(
                    surfaceSize.getWidth(),
                    surfaceSize.getHeight());
        }
    }

    @Override
    protected void ensureDestroyed() {
        super.ensureDestroyed();
        if (!isHardwareAccelerated()) {
            // Ensure it is called.
            preview.onSurfaceDestroyed();
        }
    }

    private boolean isHardwareAccelerated() {
        return preview.getView().isHardwareAccelerated();
    }
}

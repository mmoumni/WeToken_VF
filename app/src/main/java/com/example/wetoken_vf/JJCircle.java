package com.example.wetoken_vf;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import com.cjj.sva.anim.JJBaseController;

public class JJCircle  extends JJBaseController {
        private String mColor = "#3629B7";
        private int cx;
        private int cy;
        private int cr;
        private RectF mRectF = new RectF();
        private int j = 10;

        public JJCircle() {
        }

        public void draw(Canvas canvas, Paint paint) {
            canvas.drawColor(Color.parseColor(this.mColor));
            switch(this.mState) {
                case 0:
                    this.drawNormalView(paint, canvas);
                    break;
                case 1:
                    this.drawStartAnimView(paint, canvas);
                    break;
                case 2:
                    this.drawStopAnimView(paint, canvas);
            }

        }

        private void drawStopAnimView(Paint paint, Canvas canvas) {
            canvas.save();
            canvas.drawLine((this.mRectF.right + (float)this.cr - (float)this.j) * (this.mPro >= 0.2F ? this.mPro : 0.2F), this.mRectF.bottom + (float)this.cr - (float)this.j, this.mRectF.right + (float)this.cr - (float)this.j, this.mRectF.bottom + (float)this.cr - (float)this.j, paint);
            if (this.mPro > 0.5F) {
                canvas.drawArc(this.mRectF, 45.0F, -((this.mPro - 0.5F) * 360.0F * 2.0F), false, paint);
                canvas.drawLine(this.mRectF.right - (float)this.j, this.mRectF.bottom - (float)this.j, this.mRectF.right + (float)this.cr - (float)this.j, this.mRectF.bottom + (float)this.cr - (float)this.j, paint);
            } else {
                canvas.drawLine(this.mRectF.right - (float)this.j + (float)this.cr * (1.0F - this.mPro), this.mRectF.bottom - (float)this.j + (float)this.cr * (1.0F - this.mPro), this.mRectF.right + (float)this.cr - (float)this.j, this.mRectF.bottom + (float)this.cr - (float)this.j, paint);
            }

            canvas.restore();
            this.mRectF.left = (float)(this.cx - this.cr) + 240.0F * (1.0F - this.mPro);
            this.mRectF.right = (float)(this.cx + this.cr) + 240.0F * (1.0F - this.mPro);
            this.mRectF.top = (float)(this.cy - this.cr);
            this.mRectF.bottom = (float)(this.cy + this.cr);
        }

        private void drawStartAnimView(Paint paint, Canvas canvas) {
            canvas.save();
            if (this.mPro <= 0.5F) {
                canvas.drawArc(this.mRectF, 45.0F, -(360.0F - 720.0F * (this.mPro == -1.0F ? 1.0F : this.mPro)), false, paint);
                canvas.drawLine(this.mRectF.right - (float)this.j, this.mRectF.bottom - (float)this.j, this.mRectF.right + (float)this.cr - (float)this.j, this.mRectF.bottom + (float)this.cr - (float)this.j, paint);
            } else {
                canvas.drawLine(this.mRectF.right - (float)this.j + (float)this.cr * this.mPro, this.mRectF.bottom - (float)this.j + (float)this.cr * this.mPro, this.mRectF.right + (float)this.cr - (float)this.j, this.mRectF.bottom + (float)this.cr - (float)this.j, paint);
            }

            canvas.drawLine((this.mRectF.right + (float)this.cr - (float)this.j) * (1.0F - this.mPro * 0.8F), this.mRectF.bottom + (float)this.cr - (float)this.j, this.mRectF.right + (float)this.cr - (float)this.j, this.mRectF.bottom + (float)this.cr - (float)this.j, paint);
            canvas.restore();
            this.mRectF.left = (float)(this.cx - this.cr) + 240.0F * this.mPro;
            this.mRectF.right = (float)(this.cx + this.cr) + 240.0F * this.mPro;
            this.mRectF.top = (float)(this.cy - this.cr);
            this.mRectF.bottom = (float)(this.cy + this.cr);
        }

        private void drawNormalView(Paint paint, Canvas canvas) {
            this.cr = this.getWidth() / 24;
            this.cx = this.getWidth() / 2;
            this.cy = this.getHeight() / 2;
            this.mRectF.left = (float)(this.cx - this.cr);
            this.mRectF.right = (float)(this.cx + this.cr);
            this.mRectF.top = (float)(this.cy - this.cr);
            this.mRectF.bottom = (float)(this.cy + this.cr);
            canvas.save();
            paint.reset();
            paint.setAntiAlias(true);
            paint.setColor(-1);
            paint.setStrokeWidth(4.0F);
            paint.setStyle(Paint.Style.STROKE);
            canvas.rotate(45.0F, (float)this.cx, (float)this.cy);
            canvas.drawLine((float)(this.cx + this.cr), (float)this.cy, (float)(this.cx + this.cr * 2), (float)this.cy, paint);
            canvas.drawArc(this.mRectF, 0.0F, 360.0F, false, paint);
            canvas.restore();
        }

        public void startAnim() {
            if (this.mState != 1) {
                this.mState = 1;
                this.startSearchViewAnim();
            }
        }

        public void resetAnim() {
            if (this.mState != 2) {
                this.mState = 2;
                this.startSearchViewAnim();
            }
        }
    }

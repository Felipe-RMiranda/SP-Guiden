package com.mirandar.spguiden.view

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.NestedScrollView
import com.mirandar.spguiden.R
import com.mirandar.spguiden.control.Utils

class HomePage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        utils = Utils(this)
        handler = Handler()

        val btnMenuHome: ImageView = findViewById(R.id.btnMenu)
        val scrollBkg: LinearLayout = findViewById(R.id.scrollBkg) 
        scroll = findViewById(R.id.scroll)
        btnMore = findViewById(R.id.showMore)
        showMoreText = findViewById(R.id.showMoreText)
        arrowDirection = findViewById(R.id.arrowDirection)
        t1 = findViewById(R.id.tx1)
        t2 = findViewById(R.id.tx2)
        t3 = findViewById(R.id.tx3)
        t4 = findViewById(R.id.tx4)
        t5 = findViewById(R.id.tx5)

        utils!!.printImgs()
        utils!!.startCarousel()
        utils!!.localList()
        
        scroll!!.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY >= trashold) {
                scrollBkg.visibility = View.VISIBLE
            } else {
                scrollBkg.visibility = View.GONE
            }
        }
        
        animationScroll(0, 1000, 1500)

        btnMenuHome.setOnClickListener{ view ->
            showMenu(view)
        }

        btnMore!!.setOnClickListener{
            showMore()
        }
    }
    @SuppressLint("Recycle")
    private fun animationScroll(eixoYInit:Int, eixoYDest:Int, time:Long) {
        val animator = ValueAnimator.ofInt(eixoYInit, eixoYDest)
        animator.duration = time
        animator.addUpdateListener { animation ->
            val y = animation.animatedValue as Int
            scroll!!.smoothScrollTo(0, y)
        }
        animator.start()
        handler!!.postDelayed({
            quickAnimation(btnMore!!, 1500, 1)
        },time)
    }
    @SuppressLint("Recycle", "ObjectAnimatorBinding")
    private fun quickAnimation(obj: View, time: Long, repeat: Int){
        val valueAnimator = ValueAnimator.ofFloat(0f, -50f, 0f).apply {
            this.duration = time
            interpolator = BounceInterpolator()
            repeatCount = repeat
            repeatMode = ValueAnimator.RESTART
        }

        valueAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            obj.translationY = animatedValue
        }

        valueAnimator.startDelay = 100
        valueAnimator.start()
    }

    private fun showMenu(v: View){
        val popup = PopupMenu(this, v)
        popup.menuInflater.inflate(R.menu.menu_navigation, popup.menu)
        popup.setOnMenuItemClickListener {menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.mnHome -> {
                    scroll!!.smoothScrollTo(0, 0)
                    true
                }
                R.id.mnAlbum -> {
                    handler!!.postDelayed({
                        utils!!.showPopupWindow("img")
                    },300)
                    true
                }
                R.id.mnLocations -> {
                    utils!!.showPopupWindow("locations")
                    true
                }
                R.id.mnAbout -> {
                    utils!!.showPopupWindow("About")
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    private fun showMore() {
        val text = showMoreText!!.text
        if (text == showMoreClosed) {
            showMoreText!!.text = "Ver menos"
            arrowDirection!!.setImageDrawable(getDrawable(R.drawable.more_up))
            t1!!.visibility = View.VISIBLE
            t2!!.visibility = View.VISIBLE
            t3!!.visibility = View.VISIBLE
            t4!!.visibility = View.VISIBLE
            t5!!.visibility = View.VISIBLE
            animationScroll(scroll!!.scrollY, 1700, 1500)
        } else {
            showMoreText!!.text = "Ver mais"
            arrowDirection!!.setImageDrawable(getDrawable(R.drawable.more_down))
            t1!!.visibility = View.GONE
            t2!!.visibility = View.GONE
            t3!!.visibility = View.GONE
            t4!!.visibility = View.GONE
            t5!!.visibility = View.GONE
        }
    }

    override fun onPause() {
        super.onPause()
        utils!!.pauseRunnable()
    }

    override fun onResume() {
        super.onResume()
        utils!!.startRunnable()
    }
    private var utils: Utils? = null
    private var scroll: NestedScrollView? = null
    private val trashold = 400
    private var handler: Handler? = null
    private var showMoreText: TextView? = null
    private var arrowDirection: ImageView? = null
    private var btnMore: LinearLayout? = null
    private var t1: TextView? = null
    private var t2: TextView? = null
    private var t3: TextView? = null
    private var t4: TextView? = null
    private var t5: TextView? = null
    private val showMoreClosed = "Ver mais"
}
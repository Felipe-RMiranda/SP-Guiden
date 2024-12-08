package com.mirandar.spguiden.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.mirandar.spguiden.R
import com.mirandar.spguiden.control.Utils

class AboutFragment(
    private val context: Activity
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.about_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val utils = Utils(context)

        val close = view.findViewById<LinearLayout>(R.id.aboutClose)
        val info = view.findViewById<LinearLayout>(R.id.info)
        val insta = view.findViewById<ImageView>(R.id.instagram)
        val email = view.findViewById<ImageView>(R.id.email)
        val lin = view.findViewById<ImageView>(R.id.lin)
        val git = view.findViewById<ImageView>(R.id.git)

        info.setOnClickListener{}
        close.setOnClickListener{
            activity?.finish()
        }

        insta.setOnClickListener{
            utils.openWebPage(utils.getData().getLinkInsta())
        }
        email.setOnClickListener{
            utils.openWebPage(utils.getData().getLinkEmail())
        }
        lin.setOnClickListener{
            utils.openWebPage(utils.getData().getLinkLin())
        }
        git.setOnClickListener{
            utils.openWebPage(utils.getData().getLinkGit())
        }
    }
}
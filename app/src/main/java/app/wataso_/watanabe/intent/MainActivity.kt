package app.wataso_.watanabe.intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //ギャラリーの為に必要
    val readRequestCode: Int=42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // intentButtonクリック時にsecondActivityへ画面遷移
        intentButton.setOnClickListener {
            val toSecondActivityIntent = Intent(this,SecondActivity::class.java)
            startActivity(toSecondActivityIntent)


        }
        //playStoreButtonクリック時にplayストアを開く
        playStoreButton.setOnClickListener {
            val playStoreIntent = Intent(Intent.ACTION_VIEW)
            playStoreIntent.data = Uri.parse("https://play.google.com/store/apps")
            //以下の文を変えることによって開くたいアプリケーション先が指定できる
            playStoreIntent.setPackage("com.android.vending")
            startActivity(playStoreIntent)

        }

        //mapButtonクリック時に地図を開く
        mapButton.setOnClickListener {
            val mapIntent = Intent(Intent.ACTION_VIEW)
            mapIntent.data = Uri.parse("geo:35.6473,139.7360")
            //!=の間はくっつけないといけない
            if (mapIntent.resolveActivity(packageManager) != null){
                startActivity(mapIntent)
        }

        //browserButtonクリック時にブラウザを開く
            val browserIntent = Intent(Intent.ACTION_VIEW)
            //ブラウザを指定したときは表示したいWEDサイトのURLにする必要がある
            browserIntent.data = Uri.parse("https://Life=is=tech.som/")
            if (browserIntent.resolveActivity(packageManager) != null){
                startActivity(browserIntent)
            }
        }

        galleryButton.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            galleryIntent.addCategory(Intent.CATEGORY_OPENABLE)
            //タイプを指定している
            galleryIntent.type = "image/*"
            startActivityForResult(galleryIntent, readRequestCode)
        }

    }
    //遷移先のアクティビティから結果を受け取る
    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)

        if (requestCode == readRequestCode && resultCode == Activity.RESULT_OK){
            resultData?.data.also{ uri ->
                imageView.setImageURI(uri)
            }
        }
    }
}
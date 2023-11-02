package com.example.here.contentsList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.here.R
import com.example.here.utils.FBAuth
import com.example.here.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ContentsListActivity : AppCompatActivity() {

    lateinit var myRef : DatabaseReference

    val bookmarkIdList = mutableListOf<String>()

    lateinit var rvAdapter: ContentsRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_list)

        val items = ArrayList<ContentModel>()
        val itemKeyList = ArrayList<String>()

        rvAdapter = ContentsRVAdapter(baseContext, items, itemKeyList, bookmarkIdList)

        val database = Firebase.database

        val category = intent.getStringExtra("category")


        if(category == "category1") {

            myRef = database.getReference("contents")


        } else if(category == "category2") {

            myRef = database.getReference("contents2")

        } else if(category == "category3") {

            myRef = database.getReference("contents3")


        } else if(category == "category4") {

            myRef = database.getReference("contents4")

        } else if(category == "category5") {

            myRef = database.getReference("contents5")

        } else if(category == "category6") {

            myRef = database.getReference("contents6")

        } else if(category == "category7") {

            myRef = database.getReference("contents7")

        } else if(category == "category8") {

            myRef = database.getReference("contents8")

        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {
                    Log.d("ContentsListActivity", dataModel.toString())
                    Log.d("ContentsListActivity", dataModel.key.toString())
                    val item = dataModel.getValue(ContentModel::class.java)
                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())
                }
                rvAdapter.notifyDataSetChanged()
                Log.d("ContentsListActivity", items.toString())


            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)








        val rv : RecyclerView = findViewById(R.id.rv)


        rv.adapter = rvAdapter

        rv.layoutManager = GridLayoutManager(this, 2)

        getBookmarkData()




//        val myRef = database.getReference("contents")
//        myRef.push().setValue(
//            ContentModel("제주도","https://search.pstatic.net/common?src=https%3A%2F%2Fsearch.pstatic.net%2Fcommon%2F%3Fsrc%3Dhttp%253A%252F%252Fblogfiles.naver.net%252F20151202_293%252Fstyle_seul_1449044433370pc27e_JPEG%252F5%2525282%252529.JPG%26type%3Dsc960_832&type=f1040_576_domesearch", "https://travel.naver.com/domestic/14/summary?seasonIndex=2")
//        )
//
//        myRef.push().setValue(
//            ContentModel("부산","https://search.pstatic.net/common?src=https%3A%2F%2Fsearch.pstatic.net%2Fcommon%2F%3Fsrc%3Dhttp%253A%252F%252Fimgnews.naver.net%252Fimage%252F5486%252F2021%252F11%252F01%252F0000190010_003_20211101140402620.jpg%26type%3Dsc960_832&type=f1040_576_domesearch","https://travel.naver.com/domestic/08/summary?seasonIndex=2")
//        )
//
//        myRef.push().setValue(
//            ContentModel("경주","https://search.pstatic.net/common?src=https%3A%2F%2Fsearch.pstatic.net%2Fcommon%2F%3Fsrc%3Dhttp%253A%252F%252Fblogfiles.naver.net%252FMjAyMjAzMDNfNiAg%252FMDAxNjQ2MzE2MDU3NDg2.tEPkDshiSIGe69EqB2JfpgMm4UImT-Pa_8baHSPfc3Mg.BqHd7_JxVgN1wcVMBu-WU5vBeQTjeVO1QJbF_n1VPMcg.JPEG.kds4811%252FIMG_6397.jpg%26type%3Dsc960_832&type=f1040_576_domesearch","https://travel.naver.com/domestic/04130/summary?seasonIndex=2")
//        )
//
//
//        myRef.push().setValue(
//            ContentModel("오사카","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_10%2F20201111235325563_W6UQ2PZ8S.jpg%2Ffb276_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/JPOSA298566/city/summary")
//        )
//
//        myRef.push().setValue(
//            ContentModel("후쿠오카","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_13%2F20210315210846703_SGBSZOJJQ.jpg%2Ffb399_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/JPFUK298207/city/summary")
//        )
//
//        myRef.push().setValue(
//            ContentModel("도쿄","https://search.pstatic.net/common?src=http%3A%2F%2Fmedia-cdn.tripadvisor.com%2Fmedia%2Fphoto-o%2F1b%2Fde%2F4e%2F5f%2Fphoto3jpg.jpg&type=w800_travelsearch","https://travel.naver.com/overseas/JPTYO298184/city/summary")
//        )
//
//
//        myRef.push().setValue(
//            ContentModel("상하이","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_14%2F20210923172631640_2VNA0HJP2.jpg%2Ffb426_3_i2.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/CNSGH308272/city/summary")
//        )
//
//        myRef.push().setValue(
//            ContentModel("칭다오","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_14%2F20210923173348362_UQ0FZYKGY.jpg%2Ffb427_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/CNQIN297458/city/summary")
//        )
//
//        myRef.push().setValue(
//            ContentModel("톈진","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_15%2F20211101160003667_QP2ANKQKA.jpg%2Ffb469_3_i2.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/CNTNJ311293/city/summary")
//        )
//
//
//        myRef.push().setValue(
//            ContentModel("다낭","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_4%2F20200818175406414_I61U75ITC.jpg%2Ffb104_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/VNDAD298085/city/summary")
//        )
//
//        myRef.push().setValue(
//            ContentModel("하노이","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_4%2F20200818221405309_BPF6QH6G0.jpg%2Ffb108_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/VNHAN293924/city/summary")
//        )
//
//        myRef.push().setValue(
//            ContentModel("호찌민","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_3%2F20200818104640792_XCDX844N8.jpg%2Ffb112_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/VNSGN293925/city/summary")
//        )
//
//
//        myRef.push().setValue(
//            ContentModel("런던","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_1%2F20200622034932531_UAZEFB9TX.jpg%2Ffb81_3_i3.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/GBLON186338/city/summary")
//        )
//
//        myRef.push().setValue(
//            ContentModel("로마","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_6%2F20200917123635792_B6SF4A79Y.jpg%2Ffb171_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/ITQLD187791/city/summary")
//        )
//
//
//        myRef.push().setValue(
//            ContentModel("시드니","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_5%2F20200910150801575_1TGQIRU99.jpg%2Ffb165_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/AUSYD255060/city/summary")
//        )
//        myRef.push().setValue(
//            ContentModel("멜버른","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_6%2F20200917121359043_XUEQQ0H94.jpg%2Ffb168_3_i2.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/AUMEL255100/city/summary")
//        )
//
//        myRef.push().setValue(
//            ContentModel("하와이","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_2%2F20200708170934164_NM7GTZU3H.jpg%2Ffb90_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/US40128932/city/summary")
//        )
//        myRef.push().setValue(
//            ContentModel("뉴욕","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_11%2F20201127102731495_B5TZU4H7Q.jpg%2Ffb286_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/USNYC60763/city/summary")
//        )
//





//        items.add(ContentModel("제주도","https://search.pstatic.net/common?src=https%3A%2F%2Fsearch.pstatic.net%2Fcommon%2F%3Fsrc%3Dhttp%253A%252F%252Fblogfiles.naver.net%252F20151202_293%252Fstyle_seul_1449044433370pc27e_JPEG%252F5%2525282%252529.JPG%26type%3Dsc960_832&type=f1040_576_domesearch", "https://travel.naver.com/domestic/14/summary?seasonIndex=2"))
//        items.add(ContentModel("부산","https://search.pstatic.net/common?src=https%3A%2F%2Fsearch.pstatic.net%2Fcommon%2F%3Fsrc%3Dhttp%253A%252F%252Fimgnews.naver.net%252Fimage%252F5486%252F2021%252F11%252F01%252F0000190010_003_20211101140402620.jpg%26type%3Dsc960_832&type=f1040_576_domesearch","https://travel.naver.com/domestic/08/summary?seasonIndex=2"))
//        items.add(ContentModel("경주","https://search.pstatic.net/common?src=https%3A%2F%2Fsearch.pstatic.net%2Fcommon%2F%3Fsrc%3Dhttp%253A%252F%252Fblogfiles.naver.net%252FMjAyMjAzMDNfNiAg%252FMDAxNjQ2MzE2MDU3NDg2.tEPkDshiSIGe69EqB2JfpgMm4UImT-Pa_8baHSPfc3Mg.BqHd7_JxVgN1wcVMBu-WU5vBeQTjeVO1QJbF_n1VPMcg.JPEG.kds4811%252FIMG_6397.jpg%26type%3Dsc960_832&type=f1040_576_domesearch","https://travel.naver.com/domestic/04130/summary?seasonIndex=2"))
//        items.add(ContentModel("오사카","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_10%2F20201111235325563_W6UQ2PZ8S.jpg%2Ffb276_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/JPOSA298566/city/summary"))
//        items.add(ContentModel("후쿠오카","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_13%2F20210315210846703_SGBSZOJJQ.jpg%2Ffb399_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/JPFUK298207/city/summary"))
//        items.add(ContentModel("도쿄","https://search.pstatic.net/common?src=http%3A%2F%2Fmedia-cdn.tripadvisor.com%2Fmedia%2Fphoto-o%2F1b%2Fde%2F4e%2F5f%2Fphoto3jpg.jpg&type=w800_travelsearch","https://travel.naver.com/overseas/JPTYO298184/city/summary"))
//        items.add(ContentModel("상하이","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_14%2F20210923172631640_2VNA0HJP2.jpg%2Ffb426_3_i2.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/CNSGH308272/city/summary"))
//        items.add(ContentModel("칭다오","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_14%2F20210923173348362_UQ0FZYKGY.jpg%2Ffb427_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/CNQIN297458/city/summary"))
//        items.add(ContentModel("톈진","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_15%2F20211101160003667_QP2ANKQKA.jpg%2Ffb469_3_i2.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/CNTNJ311293/city/summary"))
//        items.add(ContentModel("다낭","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_4%2F20200818175406414_I61U75ITC.jpg%2Ffb104_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/VNDAD298085/city/summary"))
//        items.add(ContentModel("하노이","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_4%2F20200818221405309_BPF6QH6G0.jpg%2Ffb108_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/VNHAN293924/city/summary"))
//        items.add(ContentModel("호찌민","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_3%2F20200818104640792_XCDX844N8.jpg%2Ffb112_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/VNSGN293925/city/summary"))
//        items.add(ContentModel("런던","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_1%2F20200622034932531_UAZEFB9TX.jpg%2Ffb81_3_i3.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/GBLON186338/city/summary"))
//        items.add(ContentModel("로마","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_6%2F20200917123635792_B6SF4A79Y.jpg%2Ffb171_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/ITQLD187791/city/summary"))
//        items.add(ContentModel("시드니","https://search.pstatic.net/common?src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5885_000_5%2F20200910150801575_1TGQIRU99.jpg%2Ffb165_3_i1.jpg%3Ftype%3Dw540_fst&type=w800_travelsearch","https://travel.naver.com/overseas/AUSYD255060/city/summary"))

    }

    private fun getBookmarkData(){

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                bookmarkIdList.clear()

                for (dataModel in dataSnapshot.children) {
                    bookmarkIdList.add(dataModel.key.toString())
                }
                Log.d("Bookmark", bookmarkIdList.toString())
                rvAdapter.notifyDataSetChanged()



            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)


    }


}
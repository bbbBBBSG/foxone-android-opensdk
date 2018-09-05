package com.fox.one.opensdk.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fox.one.opensdk.APILoader
import com.fox.one.opensdk.wallet.WalletAPI
import com.fox.one.opensdk.wallet.model.WalletAssetsResponse
import com.fox.one.opensdk.wallet.model.WalletSnapshotsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //request all wallet info
        APILoader.load(WalletAPI::class.java).allWalletInfo
                .enqueue(object: Callback<WalletAssetsResponse> {
                    override fun onFailure(call: Call<WalletAssetsResponse>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<WalletAssetsResponse>, response: Response<WalletAssetsResponse>) {
                        response.body()?.data?.assets?.forEach {
                            //bind each of wallets
                        }

                    }

                })

        //request all snapshots
        APILoader.load(WalletAPI::class.java).allSnapshots
                .enqueue(object : Callback<WalletSnapshotsResponse> {
                    override fun onFailure(call: Call<WalletSnapshotsResponse>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<WalletSnapshotsResponse>, response: Response<WalletSnapshotsResponse>) {
                        response.body()?.data?.snapshots?.forEach {
                            //bin each of snapshots
                        }
                    }
                })

        //
    }
}

package tw.idv.jew.catchat

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //讓工具列視為預設的app bar，才會顯示app的名稱
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //設置工具列來加入Up按鈕並顯示你在哪個畫面
        //從瀏覽容器取得navController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        //取得抽屜的參考
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)

        //建立appBarConfiguration，將工具列接到導覽圖
        val builder = AppBarConfiguration.Builder(navController.graph)
        builder.setOpenableLayout(drawer)   //將抽屜加入AppBarConfiguration
        val appBarConfiguration = builder.build()
        //讓工具列使用appBarConfiguration
        toolbar.setupWithNavController(navController, appBarConfiguration)

        //將抽屜接到導覽控制器，讓按下項目時前往ID相符的地方
        val navView = findViewById<NavigationView>(R.id.nav_view)
        NavigationUI.setupWithNavController(navView, navController)
    }

    //將menu_toolbar的選單資源檔裡面的所有項目加入工具列
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //當工具列裡的項目被按下時，NavController會前往正確的destination
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }
}
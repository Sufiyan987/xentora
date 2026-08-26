package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.EntoraRepository
import com.example.viewmodel.EntoraViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read app name string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Entora", appName)
  }

  @Test
  fun `verify entora products repository and catalog`() {
    val products = EntoraRepository.sampleProducts
    assertTrue(products.isNotEmpty())
    val apex = products.firstOrNull { it.id == "entora-apex-h1" }
    assertNotNull(apex)
    assertEquals("ENTORA Apex H1 Pro", apex?.name)
  }

  @Test
  fun `verify view model cart and coupon logic`() {
    val vm = EntoraViewModel()
    assertTrue(vm.applyCouponCode("ENTORA15"))
    assertEquals("ENTORA15", vm.appliedCoupon.value?.code)
  }
}

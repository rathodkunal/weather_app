package com.example.weatherapplication.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.weatherapplication.R
import com.example.weatherapplication.ui.theme.MyTypography
import com.example.weatherapplication.viewmodel.WeatherViewModel

/**
 * @author Kunal Rathod
 * @since 20 March 2023
 * this class hold information of weather
 * @param weatherViewModel viewModel instance
 * */

@Composable
fun WeatherDetailCompose(weatherViewModel: WeatherViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        if (weatherViewModel.isLoading.value) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.purple_700),
                    strokeWidth = 4.dp,
                )
            }
        } else {
            weatherViewModel.weatherResponse.value?.let {

                Column(modifier = Modifier.padding(8.dp)) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Fit,
                        painter = rememberImagePainter(it.weather[0].icon?.let { it1 ->
                            stringResource(id = R.string.image_url, it1)
                        }),
                        contentDescription = "",
                        alignment = Alignment.Center
                    )


                    Text(text = "Country Info", style = MyTypography.caption)

                    Divider(
                        modifier = Modifier.padding(
                            top = 5.dp, bottom = 5.dp
                        ), color = Color.DarkGray.copy(alpha = .2f)
                    )

                    Row {
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = stringResource(R.string.city_name),
                            style = MyTypography.subtitle1
                        )
                        weatherViewModel.weatherResponse.value?.name?.let {
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp
                                ), text = it, style = MyTypography.subtitle2
                            )
                        }
                    }

                    Row {
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = stringResource(R.string.country_name),
                            style = MyTypography.subtitle1
                        )
                        weatherViewModel.weatherResponse.value?.sys?.let {
                            it.country?.let { country ->
                                Text(
                                    modifier = Modifier.padding(
                                        horizontal = 5.dp
                                    ), text = country, style = MyTypography.subtitle2
                                )
                            }
                        }
                    }

                    Row {
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = stringResource(R.string.weather),
                            style = MyTypography.subtitle1
                        )
                        weatherViewModel.weatherResponse.value?.weather?.let { item ->
                            item[0].main?.let { weather ->
                                Text(
                                    modifier = Modifier.padding(
                                        horizontal = 5.dp
                                    ), text = weather, style = MyTypography.subtitle2
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(text = stringResource(R.string.temp_info), style = MyTypography.caption)

                    Divider(
                        modifier = Modifier.padding(
                            top = 5.dp, bottom = 5.dp
                        ), color = Color.DarkGray.copy(alpha = .2f)
                    )

                    Row {
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = stringResource(R.string.minimum),
                            style = MyTypography.subtitle1
                        )
                        weatherViewModel.weatherResponse.value?.main?.let { item ->
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp
                                ),
                                text = item.tempMin.toString(),
                                style = MyTypography.subtitle2
                            )
                        }
                    }

                    Row {
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = stringResource(R.string.maximum),
                            style = MyTypography.subtitle1
                        )
                        weatherViewModel.weatherResponse.value?.main?.let { item ->
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp
                                ),
                                text = item.tempMax.toString(),
                                style = MyTypography.subtitle2
                            )
                        }
                    }


                    Row {
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = stringResource(R.string.pressure),
                            style = MyTypography.subtitle1
                        )
                        weatherViewModel.weatherResponse.value?.main?.let { item ->
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp
                                ), text = item.pressure.toString(), style = MyTypography.subtitle2
                            )
                        }
                    }

                    Row {
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = stringResource(R.string.humidity),
                            style = MyTypography.subtitle1
                        )
                        weatherViewModel.weatherResponse.value?.main?.let { item ->
                            Text(
                                modifier = Modifier.padding(
                                    horizontal = 5.dp
                                ), text = item.humidity.toString(), style = MyTypography.subtitle2
                            )
                        }
                    }
                }

            }
            /*weatherViewModel.weatherResponse.value?.let {
                it.imageBitmap.value?.asImageBitmap()
                    ?.let { it1 -> Image(bitmap = it1, contentDescription = "") }

            }*/

        }
    }

}

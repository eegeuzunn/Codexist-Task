/* 
    {
      "internationalPhoneNumber": "+90 850 441 3777",
      "formattedAddress": "Güven, Adnan Kahveci Blv. No:89, 34160 Güngören/İstanbul, Türkiye",
      "location": {
        "latitude": 41.0156193,
        "longitude": 28.878123700000003
      },
      "googleMapsUri": "https://maps.google.com/?cid=8096632163030802992",
      "websiteUri": "http://www.kalecenter.com.tr/",
      "displayName": {
        "text": "Kale Outlet Center",
        "languageCode": "tr"
      },
      "shortFormattedAddress": "Güven, Adnan Kahveci Blv. No:89, Güngören"
    }
*/


export type places = {
    internationalPhoneNumber: string;
    formattedAddress: string;
    location: {
        latitude: number;
        longitude: number;
    };
    googleMapsUri: string;
    websiteUri: string;
    displayName: {
        text: string;
        languageCode: string;
    };
    shortFormattedAddress: string;
}
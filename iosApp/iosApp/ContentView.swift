import SwiftUI
import Shared

struct ContentView: View {
    // Kotlinで定義したStoreクラスを使用
    let store = Store(id: "1", name: "池袋店", description: "データエンジニアの拠点")

    var body: some View {
        VStack(spacing: 16) {
            Image(systemName: "shop")
                .font(.system(size: 60))
            Text(store.name)
                .font(.title)
            Text(store.description)
                .foregroundColor(.gray)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
